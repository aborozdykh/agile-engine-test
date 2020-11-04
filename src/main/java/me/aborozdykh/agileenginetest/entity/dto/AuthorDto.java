package me.aborozdykh.agileenginetest.entity.dto;

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
public class AuthorDto {
    private Long id;
    private String author;
}
