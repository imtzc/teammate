package club.teammate.wx.service;

import club.teammate.wx.mapper.ActiveLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActiveLogService {
    @Autowired
    private ActiveLogMapper activeLogMapper;

    public ActiveLogService() {
    }
}