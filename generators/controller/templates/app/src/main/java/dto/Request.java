package <%= packageName %>.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class <%= configOptions.entityName %>Request {<% configOptions.fields.forEach(obj => { if(obj.key === false) {%>
    private  <%= obj.type %> <%= obj.fieldName%>;<% }}); %>
}

