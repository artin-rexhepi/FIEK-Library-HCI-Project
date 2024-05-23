package model.filter;


abstract  class Filter {
    public abstract String buildQuery();
}

public class MemberFilter extends Filter{
    private String id;
    private String emri;
    private String email;
    private String telefoni;
    private String gjinia;


    public MemberFilter(String id, String emri, String email, String telefoni, String gjinia) {
        this.id=id;
        this.emri=emri;
        this.email=email;
        this.telefoni=telefoni;
        this.gjinia=gjinia;
    }

    public String buildQuery(){
        StringBuilder query = new StringBuilder();
        if (this.id != null && !this.id.isEmpty()) {
            query.append(" AND memberid LIKE '").append(this.id).append("%' ");
        }

        if (this.emri != null && !this.emri.isEmpty()) {
            query.append(" AND name LIKE '").append(this.emri).append("%' ");
        }

        if (this.email != null && !this.email.isEmpty()) {
            query.append(" AND email LIKE '").append(this.email).append("%'");
        }

        if (this.telefoni != null && !this.telefoni.isEmpty()) {
            query.append(" AND phone LIKE '").append(this.telefoni).append("%' ");
        }

        if (this.gjinia != null && !this.gjinia.isEmpty()) {
            query.append(" AND gender LIKE '").append(this.gjinia).append("%' ");
        }

        return query.toString();
    }

}
