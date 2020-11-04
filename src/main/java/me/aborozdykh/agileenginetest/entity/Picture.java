package me.aborozdykh.agileenginetest.entity;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
@Entity
@Table(name = "pictures")
public class Picture {
    @Id
    private String id;
    @ManyToOne
    private Author author;
    @ManyToOne
    private Camera camera;
    @ManyToMany
    private List<Tag> tags;

    private String fullPicture;
    private String croppedPicture;
}
