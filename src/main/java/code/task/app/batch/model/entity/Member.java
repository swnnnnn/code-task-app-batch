package code.task.app.batch.model.entity;

import code.task.app.batch.model.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Member extends BaseTimeEntity {
    @Id
    private String id;
    private String name;
    private String email;
    private String password;
    private int rank;
    private String useYn;

    @Enumerated(EnumType.STRING)
    private RoleType role;

    public enum RoleType {

        ROLE_ADMIN("ROLE_ADMIN"),
        ROLE_MEMBER("ROLE_MEMBER");

        private String code;

        RoleType(String code){
            this.code = code;
        }

        public String getCode(){
            return code;
        }
    }

    public void changeRank(int rank){
        this.rank = rank;
    }
}