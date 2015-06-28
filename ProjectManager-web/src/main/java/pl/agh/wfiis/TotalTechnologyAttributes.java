/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.agh.wfiis;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import pl.agh.wfiis.database.Project;
import pl.agh.wfiis.database.Technology;
import pl.agh.wfiis.model.ProjectDatabaseController;

/**
 *
 * @author hawker
 */
@Named(value = "totalTechnologyAttributes")
@SessionScoped
public class TotalTechnologyAttributes implements Serializable {
    @EJB
    private ProjectDatabaseController projectDatabaseControler;
    public Technology getTechnologyById(int id){
        return projectDatabaseControler.getTechnologyById(id);
    }
    public List<Project> getProjectTechnologyListById(int id){
        return projectDatabaseControler.getProjectListByTechnologyId(id);
    }
}
