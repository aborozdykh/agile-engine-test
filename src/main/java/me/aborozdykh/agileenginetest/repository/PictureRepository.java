package me.aborozdykh.agileenginetest.repository;

import me.aborozdykh.agileenginetest.entity.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Andrii Borozdykh
 */
public interface PictureRepository extends JpaRepository<Picture, String> {
}
