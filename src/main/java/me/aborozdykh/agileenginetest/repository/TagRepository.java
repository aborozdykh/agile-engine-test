package me.aborozdykh.agileenginetest.repository;

import me.aborozdykh.agileenginetest.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Andrii Borozdykh
 */
public interface TagRepository extends JpaRepository<Tag, Long> {
}
