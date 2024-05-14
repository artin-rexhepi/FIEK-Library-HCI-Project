package model.dto;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

public class MemberDto {
    private String IDstudendore;
    private String emri;
    private String email;
    private String gjinia;
    private String numerTelefoni;

    public MemberDto(){
    }

    public MemberDto(String IDstudendore, String emri, String email, String gjinia, String numerTelefoni) {
        this.IDstudendore = IDstudendore;
        this.emri = emri;
        this.email = email;
        this.gjinia = gjinia;
        this.numerTelefoni = numerTelefoni;
    }

    public String getIDstudendore() {
        return IDstudendore;
    }

    public String getEmri() {
        return emri;
    }

    public String getEmail() {
        return email;
    }

    public String getGjinia() {
        return gjinia;
    }

    public String getNumerTelefoni() {
        return numerTelefoni;
    }

    public void setIDstudendore(String IDstudendore) {
        this.IDstudendore = IDstudendore;
    }

    public void setEmri(String emri) {
        this.emri = emri;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGjinia(String gjinia) {
        this.gjinia = gjinia;
    }

    public void setNumerTelefoni(String numerTelefoni) {
        this.numerTelefoni = numerTelefoni;
    }
}
