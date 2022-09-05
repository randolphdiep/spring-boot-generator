package <%= packageName %>.repositories;
import <%= packageName %>.entities.<%= configOptions.entityName %>;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

<% configOptions.fields.forEach(obj => { if(obj.key === true) {%>  
public interface <%= configOptions.entityName %>Repository extends JpaRepository<<%= configOptions.entityName %>, <%= obj.type%><% }}); %>> {
    <% configOptions.fields.forEach(obj => { if(obj.fieldName.includes("Name")) {%>  
    public List<<%= configOptions.entityName %>> search<%= obj.fieldVarName %>(<%= obj.type%> <%= obj.fieldName%>);<% }}); %>
}
