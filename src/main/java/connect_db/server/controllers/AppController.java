package connect_db.server.controllers;

import connect_db.server.models.App;
import connect_db.server.models.Child;
import connect_db.server.service.AppService;
import connect_db.server.service.ChildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/app")
public class AppController {

    @Autowired
    private final ChildService childService;

    @Autowired
    private final AppService appService;

    public AppController(ChildService childService, AppService appService) {
        this.childService = childService;
        this.appService = appService;
    }

    @GetMapping("/findAll")
    public List<App> apps() {
        return appService.findAll();
    }

    @PostMapping("/createApp")
    public App create(@RequestBody App app, @RequestParam(name = "idChild") Long idChild) {
        Child child = childService.getChildById(idChild);
        app.setChild(child);
        return appService.create(app);
    }

    @PostMapping("/updateApp")
    public String updateApp(@RequestParam(name = "last_time_used") Long last_time_used,
                            @RequestParam(name = "total_time") Long total_time,
                            @RequestParam(name = "name") String name,
                            @RequestParam(name = "idChild") Long idChild) throws UnsupportedEncodingException {
        String appname = new String(Base64.getUrlDecoder().decode(name), "UTF-8");
        int res = appService.updateApp(last_time_used, total_time, appname, idChild);
        if (res != 0)
            return "update";
        return null;
    }

    @GetMapping("/checkApp")
    public Long checkApp(@RequestParam(name = "name") String name,
                         @RequestParam(name = "idChild") Long idChild) throws UnsupportedEncodingException {
        String appname = new String(Base64.getUrlDecoder().decode(name), "UTF-8");
        String res = appService.chekExistApp(appname, idChild);
        if (res != null) {
            return Long.valueOf(res);
        } else
            return null;
    }

    @GetMapping("/getApps")
    public String getApps (@RequestParam(name = "idChild") Long idChild) {
        System.out.println("ID: "+idChild);
        Child child = childService.getChildById(idChild);
        return appService.getAllByIdChild(child);
    }
}
