package <%= packageName %>.services;

import <%= packageName %>.dto.<%= configOptions.entityName %>Request;
import <%= packageName %>.dto.<%= configOptions.entityName %>Response;
import <%= packageName %>.entities.<%= configOptions.entityName %>;
import <%= packageName %>.repositories.<%= configOptions.entityName %>Repository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class <%= configOptions.entityName %>Service {
    
    private final <%= configOptions.entityName %>Repository <%= configOptions.entityVarName%>Repository;

    public void create<%= configOptions.entityName %>(<%= configOptions.entityName %>Request <%= configOptions.entityVarName%>Request){
        <%= configOptions.entityName %> <%= configOptions.entityVarName %> = <%= configOptions.entityName %>.builder()
        <% configOptions.fields.forEach(obj => { if(obj.key === false) {%>  
                .<%= obj.fieldName %>(<%= configOptions.entityVarName %>Request.get<%= obj.fieldVarName %>()) <% }}); %>      
                .build();

        <%= configOptions.entityVarName%>Repository.save(<%= configOptions.entityVarName%>);
    }
    public void edit<%= configOptions.entityName %>(<%= configOptions.entityName %>Response <%= configOptions.entityVarName %>Response){
        <%= configOptions.entityName %> <%= configOptions.entityVarName %> = <%= configOptions.entityName %>.builder()
<% configOptions.fields.forEach(obj => { %>
                .<%= obj.fieldName %>(<%= configOptions.entityVarName %>Response.get<%= obj.fieldVarName %>()) <% }); %>
                .build();

        <%= configOptions.entityVarName%>Repository.save(<%= configOptions.entityVarName%>);
    }

    public List<<%= configOptions.entityName %>Response> getAll<%= configOptions.entityName %>s(){
        List<<%= configOptions.entityName %>> <%= configOptions.entityVarName %>s = <%= configOptions.entityVarName %>Repository.findAll();
        return <%= configOptions.entityVarName %>s.stream().map(this::mapTo<%= configOptions.entityName %>Response).toList();
    }

    private <%= configOptions.entityName %>Response mapTo<%= configOptions.entityName %>Response(<%= configOptions.entityName %> <%= configOptions.entityVarName %>) {
        return <%= configOptions.entityName %>Response.builder()
        <% configOptions.fields.forEach(obj => { %>
                .<%= obj.fieldName %>(<%= configOptions.entityVarName %>.get<%= obj.fieldVarName %>()) <% }); %>
                .build();
    }

    public <%= configOptions.entityName %>Response find<%= configOptions.entityName %>ById(Integer id) {
        <%= configOptions.entityName %> <%= configOptions.entityVarName %> = <%= configOptions.entityVarName %>Repository.findById(id).get();
        return <%= configOptions.entityName %>Response.builder()
        <% configOptions.fields.forEach(obj => { %>
                .<%= obj.fieldName %>(<%= configOptions.entityVarName %>.get<%= obj.fieldVarName %>())<%});%>
                .build();
    }
    <% configOptions.fields.forEach(obj => { if(obj.fieldName.includes('Name')) {%>  
    public List<<%= configOptions.entityName %>Response> search<%= obj.fieldVarName %>(<%= obj.type %> <%= obj.fieldName %>){
		// return <%= configOptions.entityVarName %>Repository.seach<%= obj.fieldVarName %>("%"+<%= obj.fieldName %>+"%"); 
        List<<%= configOptions.entityName %>> <%= configOptions.entityVarName %>s = <%= configOptions.entityVarName %>Repository.search<%= obj.fieldVarName %>("%"+<%= obj.fieldName %>+"%");
        return <%= configOptions.entityVarName %>s.stream().map(this::mapTo<%= configOptions.entityName %>Response).toList();
	}<% }}); %> 

    public void delete<%= configOptions.entityName %>ById(Integer id){
        <%= configOptions.entityVarName %>Repository.deleteById(id);
    }
    
}
