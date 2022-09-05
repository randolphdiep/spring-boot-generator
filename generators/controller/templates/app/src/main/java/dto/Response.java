package <%= packageName %>.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class <%= configOptions.entityName %>Response {<% configOptions.fields.forEach(obj => {%>
    private  <%= obj.type %> <%= obj.fieldName%>;<% }); %>
}
