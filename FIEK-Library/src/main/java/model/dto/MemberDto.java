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

    public MemberDto(String IDstudendore, String emri, String email, String numerTelefoni, String gjinia) {
        this.IDstudendore = IDstudendore;
        this.emri = emri;
        this.email = email;
        this.numerTelefoni = numerTelefoni;
        this.gjinia = gjinia;
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

    public String getNumerTelefoni() {
        return numerTelefoni;
    }

    public String getGjinia() {
        return gjinia;
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
