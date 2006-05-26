package uk.org.catnip.eddie;

public class Category {
private String term;
private String schedule;
private String label;
public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    sb.append("term: '" + this.term+ "', ");
    sb.append("schedule: '" + this.schedule+ "', ");
    sb.append("label: '" + this.label +"'");
    sb.append("}");
    return sb.toString();
}
public String getLabel() {
    return label;
}
public void setLabel(String label) {
    this.label = label;
}
public String getSchedule() {
    return schedule;
}
public void setSchedule(String schedule) {
    this.schedule = schedule;
}
public String getTerm() {
    return term;
}
public void setTerm(String term) {
    this.term = term;
}
}
