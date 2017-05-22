package club.teammate.wx.controller;

import club.teammate.wx.domain.Active;
import club.teammate.wx.service.ActiveLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/active_log"})
public class ActiveLogController {
    @Autowired
    private ActiveLogService activeLogService;

    public ActiveLogController() {
    }

    @ResponseBody
    @RequestMapping({"/check_join"})
    public Active checkJoin(@RequestParam int userId) {
        Active active = null;
        return (Active)active;
    }
}
