package pl.agh.wfiis.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import pl.agh.wfiis.database.Asksforcollaboration;
import pl.agh.wfiis.database.Invitestoprojects;
import pl.agh.wfiis.database.Module;
import pl.agh.wfiis.database.Project;
import pl.agh.wfiis.database.TechnologiesToModules;
import pl.agh.wfiis.database.Technology;
import pl.agh.wfiis.facades.ProjectFacade;
import pl.agh.wfiis.facades.UserFacade;
import pl.agh.wfiis.database.User;
import pl.agh.wfiis.database.UsersToModules;
import pl.agh.wfiis.facades.AsksforcollaborationFacade;
import pl.agh.wfiis.facades.InvitestoprojectsFacade;
import pl.agh.wfiis.facades.ModuleFacade;
import pl.agh.wfiis.facades.TechnologyFacade;
import pl.agh.wfiis.facades.UsersToModulesFacade;

/**
 * Stateless bean for all kind of operations on the database connected with registered projects.
 */
@Stateless
@LocalBean
public class ProjectDatabaseController {
    
    /**
     * Logger object for log operations which facilitates development process.
     */
    Logger logger = Logger.getLogger(getClass().getName());
    
    /**
     * Facade to database for Project class.
     */
    @EJB
    private ProjectFacade projectFacade;
    
    /**
     * Facade to database for User class.
     */
    @EJB 
    private UserFacade userFacade;
    
    /**
     * Facade to database for Module class.
     */
    @EJB
    private ModuleFacade moduleFacade;
    
    /**
     * Facade to database for Techbnology class.
     */
    @EJB
    private TechnologyFacade technologyFacade;
    
    
    /**
     * Facade to database for Asksforcollaboration class.
     */
    @EJB
    private AsksforcollaborationFacade asksforcollaborationFacade;
    
    
    /**
     * Facade to database for Invitestoprojects class.
     */
    @EJB
    private InvitestoprojectsFacade invitestoprojectsFacade;
    
    /**
     * Facade to database for UsersToModules class.
     */
    @EJB
    private UsersToModulesFacade usersToModulesFacade;
    
    /**
     * Retrieves registered projects from the database.
     * 
     * @return List of registered projects.
     */
    public List<Project> getAllProjects() {
        List<Project> listOfAllProjects = projectFacade.findAll();      
        return listOfAllProjects;
    }
         
    /**
     * Functin returns random project list from the database.
     * 
     * @param number    Number of projects to be returned.
     * @return          List of random registered projects. 
     */
    public List<Project> getRandomProjects(int number) {
        int projectCount = projectFacade.count();
        List<Project> listOfRandomProjects = new ArrayList<>();
        
        if (projectCount < number) {
            return listOfRandomProjects;
        }
        
        Random r = new Random();
        for(int i = 0; i < number; ++i){
            int randomId = r.nextInt(projectCount) + 1;
            while (listOfRandomProjects.contains(projectFacade.find(randomId))) {
                randomId = r.nextInt(projectCount) + 1;
            }
            listOfRandomProjects.add(projectFacade.find(randomId));
        }
        return listOfRandomProjects;
    }
    
    /**
     * Function searches leader tuple in the databese by its id.
     * Then properly filled Project object is added to the database.
     * 
     * @param newProject Filled object representing project to be added.
     * @param leaderId   Project leader ID. 
     */
    public void createNewProjectInDatabase(Project newProject, int leaderId){
        User leader = userFacade.find(leaderId);
        newProject.setLeaderid(leader);
        projectFacade.create(newProject);   
        logger.info("New project should be registrated in the database");
    }
    
    /**
     * Retrieve Project object from the database by its ID.
     * 
     * @param Id    Id of the Project tuple to be found.
     * @return      Founded Project object.
     */
    public Project getProjectByID(int Id){
        return projectFacade.find(Id);
    }
    
    /**
     * Retrieve Module object from the database by its ID.
     * 
     * @param Id    Id of the Module tuple to be found.
     * @return      Founded Module object.
     */
    public Module getModuleByID(int Id) {
        return moduleFacade.find(Id);
    }
    
    /**
     * Rertrieve Technology object from the database by its ID.
     * 
     * @param Id    Id of the Technology tuple to be found.
     * @return      Founded Technology object.
     */
    public Technology getTechnologyById(int Id) {
        return technologyFacade.find(Id);
    }
    
    /**
     * Retrieve User object from the databae by its ID.
     * 
     * @param id    If if the User tuple to be found.
     * @return      Founded User object.
     */
    public User getUserById(int id){
        return userFacade.find(id);
    }
    
    /**
     * Get leader project.
     * 
     * @param ID Leader ID.
     * @return   Found project object.
     */
    public List<Project> getLeadersProjects(int ID) {
        List<Project> projects = projectFacade.findAll();
        
        for (Iterator<Project> iterator = projects.listIterator(); iterator.hasNext(); ) {
            Project project = iterator.next();
            if (project.getLeaderid().getUserid() != ID) {
                iterator.remove();
            }
        }
        
        return projects;
    }
    
    /**
     * Send project object to facade.
     * 
     * @param project Project to be modified.
     */
    public void modifyProject(Project project) {
        projectFacade.edit(project);
    }

    /**
     * Send module object to facade. Creates new module in the database.
     * 
     * @param module New module object.
     */
    public void createModule(Module module) {
        moduleFacade.create(module);
    }
    
    /**
     * Send module object to facade. Modifies existing object in the database.
     * 
     * @param module Module object to be modified.
     */
    public void modifyModule(Module module) {
        moduleFacade.edit(module);
    }
    
    /**
     * Retrieves list of projects by technology ID.
     * 
     * @param id Technology ID.
     * @return   List of found projects.   
     */
    public List<Project> getProjectListByTechnologyId(int id){
        Technology t = technologyFacade.find(id);
        HashSet<Project> projectDictionary = new HashSet<>();
        for(TechnologiesToModules ttm :t.getTechnologiesToModulesCollection()){
            projectDictionary.add(ttm.getModuleid().getProjectid());
        }
        List<Project> list = new ArrayList<>(projectDictionary);
        return list;
    }
    /**
     * Gets uniqe list of projects in which user is
     * 
     * @param id
     * @return 
     */
    public List<Project> getProjectListByUserId(int id){
        User u = userFacade.find(id);
        
        HashSet<Project> projectDictionary = new HashSet<>();
        for(UsersToModules ttm : u.getUsersToModulesCollection()){
            projectDictionary.add(ttm.getModuleid().getProjectid());
        }
        List<Project> list = new ArrayList<>(projectDictionary);
        return list;

    }
    /**
     * Checks if user is already part of the module.
     * 
     * @param u User object.
     * @param m Module object.
     * @return  true if user belongs to given module. False otherwise.
     */
    public boolean isUserInModule(User u, Module m){
        if (u.getUsersToModulesCollection() == null){
            return false;
        }
        for(UsersToModules utm : u.getUsersToModulesCollection())
        {
            if(utm.getModuleid().equals(m)) return true;
         
        }
        return false;
    }
    
    public void userApplyToModule(User u, Module m){
        Asksforcollaboration asks = new Asksforcollaboration();
        asks.setModuleid(m);
        asks.setUserid(u);
        asksforcollaborationFacade.create(asks);
    }
    
    /**
     * Checks if given user has already applied to given module.
     * 
     * @param u Uesr object.
     * @param m Module object.
     * @return true if already applied. False otherwise.
     */
    public boolean userAlreadyApplied(User u, Module m){
        for(Asksforcollaboration askfc : u.getAsksforcollaborationCollection()){
            if(askfc.getModuleid().equals(m)) return true;
        }
        return false;
    }

    /**
     * Gets non repeating list of already lidered modules by user.
     * 
     * @param u User object.
     * @return 
     */
    public List<Module> getAllLideredModules(User u) {
        HashSet<Module> moduleDictionary = new HashSet<>();
        for(Project p : u.getProjectCollection()){
            moduleDictionary.addAll(p.getModuleCollection());
        }
        List<Module> list = new ArrayList<>(moduleDictionary);
        return list;
    }

    
    /**
     * Invites user to given module
     * @param u
     * @param m 
     */
    public void inviteUserToModule(User u, Module m) {
        Invitestoprojects inv = new Invitestoprojects();
        inv.setUserid(u);
        inv.setModuleid(m);
        invitestoprojectsFacade.create(inv);
    }

    /**
     * Checks if given user is already invited to given module
     * @param u
     * @param m
     * @return true if already invited
     */
    public boolean userAlreadyInvited(User u, Module m) {
        for(Invitestoprojects inv : u.getInvitestoprojectsCollection()){
            if (m.getModuleid().equals(inv.getModuleid().getModuleid())) return true;
        }
        return false;
    }
    
    public Invitestoprojects getInvitestoprojectById(int id){
        return invitestoprojectsFacade.find(id);
    }
    
    /**
     * Adds user to module by invitation to module
     * @param inv 
     */
    public void addUserToModule(Invitestoprojects inv) {
        UsersToModules utm = new UsersToModules();
        utm.setModuleid(inv.getModuleid());
        utm.setUserid(inv.getUserid());
        usersToModulesFacade.create(utm);
    }

    public void deleteInvitation(Invitestoprojects inv) {
       invitestoprojectsFacade.remove(inv);
    }

    /**
     * Adds user to module by ask for collaboration
     * @param ask 
     */
    public void addUserToModule(Asksforcollaboration ask) {
        UsersToModules utm = new UsersToModules();
        utm.setModuleid(ask.getModuleid());
        utm.setUserid(ask.getUserid());
        usersToModulesFacade.create(utm);
    }

    /**
     * deletes ask for collaboration
     * @param ask 
     */
    public void deleteAsk(Asksforcollaboration ask) {
        asksforcollaborationFacade.remove(ask);
    }
    
    /**
     * returns ask for collaboration by its id
     * @param id
     * @return ask for collaboration
     */
    public Asksforcollaboration getAsksforcollaborationById(int id){
        return asksforcollaborationFacade.find(id);
    }
    
    /**
     * Removes module from the database.
     * 
     * @param moduleID Module ID to be romeved.
     */
    public void deleteModule(int moduleID) {
        moduleFacade.remove(moduleFacade.find(moduleID));
    }
}
