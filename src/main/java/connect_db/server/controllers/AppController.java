package connect_db.server.controllers;

import connect_db.server.models.App;
import connect_db.server.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app")
public class AppController {
    private final AppService appService;

    @Autowired
    public AppController(AppService appService) {
        this.appService = appService;
    }

    @GetMapping("/findAll")
    public List<App> apps () {
        return appService.findAll();
    }

    @PostMapping("/createApp")
    public App create (@RequestBody App app){
        return appService.create(app);
    }
}
