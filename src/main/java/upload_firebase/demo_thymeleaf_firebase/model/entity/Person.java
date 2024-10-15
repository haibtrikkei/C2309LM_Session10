package upload_firebase.demo_thymeleaf_firebase.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "persons")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Person {
    @Id
    @Column(name = "person_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer personId;
    @Column(name = "fullName",length = 70, nullable = false)
    @NotBlank(message = "Full name is empty!")
    private String fullName;
    @Column(name = "gender")
    @NotNull(message = "Gender is null")
    private Boolean gender;
    @Column(name = "birthday")
    @NotNull(message = "Birthday is null")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "Birthday is not valid")
    private Date birthday;
    @Column(name = "address", length = 200)
    @NotBlank(message = "Address is empty!")
    private String address;
    @Column(name = "avatar")
    private String avatar;
}
