package com.nanhng.FastFood.service;

import com.nanhng.FastFood.dto.constant.RoleType;
import com.nanhng.FastFood.entity.user.User;
import com.nanhng.FastFood.exception.LovelyException;
import com.nanhng.FastFood.security.SecurityContexts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BaseService {

    protected User getUser(){
        try{
            return (User) SecurityContexts.getContext().getData();
        }
        catch(Exception e){
            log.error(e.getMessage());
        }
        throw new LovelyException("Unauthorized", HttpStatus.UNAUTHORIZED);
    }

    protected User getUser(RoleType... roleTypes){
        try{
            User user = (User) SecurityContexts.getContext().getData();
            if(user.getRole() == null){
                return user;
            }
            for(RoleType role : roleTypes){
                if(user.getRole() == role) {
                    return user;
                }
            }
        }
        catch(Exception e){
            log.error(e.getMessage());
        }
        throw new LovelyException("Unauthorized", HttpStatus.UNAUTHORIZED);
    }

}
