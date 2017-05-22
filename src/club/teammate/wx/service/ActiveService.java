package club.teammate.wx.service;

import club.teammate.wx.domain.Active;
import club.teammate.wx.mapper.ActiveMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActiveService {
    @Autowired
    private ActiveMapper activeMapper;

    public ActiveService() {
    }

    public Active selectTodayActive() {
        Active active = this.activeMapper.selectTodayActive();
        if(active == null) {
            active = new Active();
        }

        return active;
    }
}
