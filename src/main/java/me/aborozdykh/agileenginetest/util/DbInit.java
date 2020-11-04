package me.aborozdykh.agileenginetest.util;

import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Andrii Borozdykh
 */
@Component
public class DbInit {
    private final AuthUtil authUtil;
    private final PictureUtil pictureUtil;
    @Value("${apiKey}")
    private String apiKey;

    public DbInit(AuthUtil authUtil, PictureUtil pictureUtil) {
        this.authUtil = authUtil;
        this.pictureUtil = pictureUtil;
    }

    @PostConstruct
    private void dbInitialize() {
        String token = authUtil.getBearerToken(apiKey);
        List<String> pictures = pictureUtil.getPicturesId(token);
    }
}
