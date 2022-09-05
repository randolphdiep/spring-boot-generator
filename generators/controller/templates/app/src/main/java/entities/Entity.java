package <%= packageName %>.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name = "<%= configOptions.tableName %>")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder   
public class <%= configOptions.entityName %> { <% configOptions.fields.forEach(obj => { if(obj.key === true) {%>
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) <% } if(obj.nullAble === false) {%>
    @Column(nullable = false)<% } %>        
    private  <%= obj.type %> <%= obj.fieldName%>;<% }); %>
}
