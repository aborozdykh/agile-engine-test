package me.aborozdykh.agileenginetest.entity.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Andrii Borozdykh
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PictureDto {
    private Long id;
    private Long authorId;
    private Long cameraId;
    private List<Long> tagsId;
    private String fullName;
    private String croppedName;
}
