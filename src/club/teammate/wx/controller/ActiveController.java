package club.teammate.wx.controller;

import club.teammate.wx.domain.Active;
import club.teammate.wx.service.ActiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/active"})
public class ActiveController {
    @Autowired
    private ActiveService activeService;

    public ActiveController() {
    }

    @ResponseBody
    @RequestMapping({"/today"})
    public Active selectTodayActive() {
        Active active = this.activeService.selectTodayActive();
        return active;
    }
}
