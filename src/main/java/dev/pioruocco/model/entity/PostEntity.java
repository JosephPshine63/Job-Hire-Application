package dev.pioruocco.model.entity;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/*
mongoDB has the special feature of creating two replicas of your existent database
 */

//this class provides as an entity for the job-posts of the application
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "JobPost")
@CrossOrigin
public class PostEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 2591076557611269375L;

    @NotNull(message = "Profile cannot be empty")
    private String profile;

    @Size(min = 25, max = 100, message = "25 charachters min and 100 charachters max")
    @NotNull(message = "Description cannot be empty")
    private String desc;

    @NotNull(message = "Experience for the job must be provided")
    private int exp;

    @NotNull(message = "Technologies cannot be empty")
    private List<String> techs;

}
