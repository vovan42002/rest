package connect_db.server.controllers;

import connect_db.server.models.Child;
import connect_db.server.service.ChildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/child")
public class ChildController {

    private final ChildService childService;

    @Autowired
    public ChildController(ChildService childService){
        this.childService = childService;
    }

    @GetMapping("/findById")
    public String findChildById(@RequestParam(name = "id") Long id){
        return childService.findByID(id);
    }
    @PostMapping("create")
    public Child create(@RequestBody Child child){
        return childService.saveChild(child);
    }
}
