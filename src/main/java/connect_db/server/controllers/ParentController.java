package connect_db.server.controllers;

import connect_db.server.models.Child;
import connect_db.server.models.Parent;
import connect_db.server.service.ChildService;
import connect_db.server.service.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/parent")
public class ParentController {

    private final ParentService parentService;

    @Autowired
    public ParentController(ParentService parentService) {
        this.parentService = parentService;
    }

    @Autowired
    private ChildService childService;

    @GetMapping("/parents")
    public List<Parent> findAll() {
        List<Parent> parents = parentService.findAll();
        return parents;
    }

    // Поиск по id
    @GetMapping("/find")
    public String findParentById(@RequestParam(name = "id") Long id) {
        return parentService.findById(id);
    }


    // проверка на существование пользователя по емайл и паролю
    @GetMapping("checkParent")
    public boolean checkParent(@RequestParam(name = "email") String email,
                               @RequestParam(name = "password") String password) {
        System.out.println("result: " + parentService.checkParent(email, password));
        if (parentService.checkParent(email, password).hashCode() == "1".hashCode()) {
            return true;
        } else
            return false;
    }

    @GetMapping("/existNick")
    public boolean existNick(@RequestParam(name = "nickname") String nick) {
        if (parentService.existNickParent(nick).hashCode() == "0".hashCode())
            return false;
        return true;
    }

    //Проверка на существование по id
    @GetMapping("/exist")
    public boolean existParent(@RequestParam(name = "id") Long id) {
        return parentService.existById(id);
    }

    // создание нового родителя
    @PostMapping("/create")
    public Parent createParent(@RequestBody Parent parent) {
        parentService.saveParent(parent);
        return parent;
    }

    //проверка на существовании в базе данных почты
    @GetMapping("/existEmail")
    public boolean existEmail(@RequestParam(name = "email") String email) {
        if (parentService.existEmail(email).hashCode()=="1".hashCode())
            return true;
        return false;
    }
    @PostMapping("/testAdd")
    public Child test(@RequestParam(name = "idParent") Long idParent,
                      @RequestParam(name = "name") String name,
                      @RequestParam(name = "latitude") String latitude,
                      @RequestParam(name = "longitude") String longitude){
        Parent p = parentService.getParentById(idParent);
        return childService.saveChild(parentService.create(name,latitude,longitude,p));
    }

    @GetMapping("/listChilds")
    public String listChild(@RequestParam(name = "idParent") Long idParent){
        System.out.println(parentService.childList(idParent));
        return parentService.childList(idParent);
    }

    @GetMapping("/getParentId")
    public Long getParentId (@RequestParam(name = "email") String email,
                             @RequestParam(name = "password") String password){
        return Long.valueOf(Integer.parseInt(parentService.getParentIdByEmailAndPassword(email,password)));
    }

    @GetMapping("/checkExistChild")
    public boolean checkExistChild(@RequestParam(name = "name") String name,
                                   @RequestParam(name = "idParent") Long idParent){
        return  parentService.checkExistChild(idParent,name);
    }
}
