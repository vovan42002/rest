package connect_db.server.controllers;

import connect_db.server.models.Child;
import connect_db.server.service.ChildService;
import connect_db.server.service.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/child")
public class ChildController {

    private final ChildService childService;

    @Autowired
    public ChildController(ChildService childService, ParentService parentService){
        this.childService = childService;
        this.parentService = parentService;
    }

    @Autowired
    private final ParentService parentService;


    @GetMapping("/findById")
    public String findChildById(@RequestParam(name = "id") Long id){
        return childService.findByID(id);
    }

    @PostMapping("/create")
    public Child create(@RequestBody Child child){
        return childService.saveChild(child);
    }

    @GetMapping("/findAll")
    public List<Child> childList(){
        return childService.findAll();
    }

    @PutMapping("/update")
    public String update(@RequestParam(name = "latitude") String latitude,
                         @RequestParam(name = "longitude") String longitude,
                         @RequestParam(name = "id") Long id){
        return childService.update(latitude,longitude,id);
    }

    @PostMapping("/add")
    public Child add (@RequestBody Child child, @RequestParam("parent_id") Long parent_id){
        child.setParent(parentService.getParentById(parent_id));
        return childService.saveChild(child);
    }
}
