package hlushakovaM.model;

import hlushakovaM.enums.FamilyRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;

@Entity
@Table(name = "familyMembers")
public class FamilyMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Имя обязательно")
    @Size(min = 2, max = 50, message = "Имя должно содержать от 2 до 50 символов.")
    private String name;

    @Min(value = 0, message = "Возраст должен быть не менее 0")
    @Max(value = 120, message = "Возраст должен быть не более 120 лет.")
    private Integer age;


    private FamilyRole role;

    @ManyToOne
    @JoinColumn(name = "house_id")
    private House house;

    public FamilyMember() {
    }

    public FamilyMember(String name, Integer age, FamilyRole role, House house) {
        this.name = name;
        this.age = age;
        this.role = role;
        this.house = house;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public FamilyRole getRole() {
        return role;
    }

    public void setRole(FamilyRole role) {
        this.role = role;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    @Override
    public String toString() {
        return "FamilyMember{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", role=" + role +
                ", house=" + house +
                '}';
    }
}
