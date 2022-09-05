package <%= packageName %>.controllers;

import <%= packageName %>.dto.<%= configOptions.entityName%>Request;
import <%= packageName %>.dto.<%= configOptions.entityName%>Response;
import <%= packageName %>.services.<%= configOptions.entityName%>Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/<%= configOptions.entityVarName%>")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class <%= configOptions.entityName%>Controller {

    private final <%= configOptions.entityName%>Service <%= configOptions.entityVarName%>Service;

    @PostMapping
    public ResponseEntity<String> create<%= configOptions.entityName%>(@RequestBody <%= configOptions.entityName%>Request <%= configOptions.entityVarName%>Request){
        <%= configOptions.entityVarName%>Service.create<%= configOptions.entityName%>(<%= configOptions.entityVarName%>Request);
        return new ResponseEntity<>("<%= configOptions.entityName%> is saved", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<<%= configOptions.entityName%>Response>> getAll<%= configOptions.entityName%>s(){
        return new ResponseEntity<>(<%= configOptions.entityVarName%>Service.getAll<%= configOptions.entityName%>s(), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<String> update<%= configOptions.entityName%>(@PathVariable Integer id, @RequestBody <%= configOptions.entityName%>Request <%= configOptions.entityVarName%>Request) {
        <%= configOptions.entityName%>Response <%= configOptions.entityVarName%>Response = <%= configOptions.entityVarName%>Service.find<%= configOptions.entityName%>ById(id).builder()
        <% configOptions.fields.forEach(obj => { if(obj.key === true) {%> 
                .<%= obj.fieldName %>(id)<% } else { %>
                .<%= obj.fieldName %>(<%= configOptions.entityVarName %>Request.get<%= obj.fieldVarName %>()) <% }}); %>
                .build();
        <%= configOptions.entityVarName%>Service.edit<%= configOptions.entityName%>(<%= configOptions.entityVarName%>Response);
        return new ResponseEntity<>("Edit success!", HttpStatus.OK);
    }

	@GetMapping(value = "/search")
	public ResponseEntity<List<<%= configOptions.entityName %>Response>> search(@RequestParam(required = false) String word) throws Exception {
        return new ResponseEntity<>( <%= configOptions.entityVarName%>Service.search<%= configOptions.entityName%>Name(word), HttpStatus.OK);
	}

    @GetMapping(value ="/{id}")
    public ResponseEntity<<%= configOptions.entityName%>Response> find<%= configOptions.entityName%>ById(@PathVariable Integer id) throws Exception {
        return new ResponseEntity<>( <%= configOptions.entityVarName%>Service.find<%= configOptions.entityName%>ById(id), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete<%= configOptions.entityName%>ById(@PathVariable Integer id){
        <%= configOptions.entityVarName%>Service.delete<%= configOptions.entityName%>ById(id);
        return new ResponseEntity<>("Delete ok", HttpStatus.OK);
    }

}
