package com.devs.demoCours.infraestructure.helpers;

import com.devs.demoCours.utils.exeptions.IdNoExist;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DeleteEntityHelp {
    public <T, ID> Map<String, Object> deleteEntity(CrudRepository<T, ID> repository, ID id, String entityName) {
        boolean exists = repository.existsById(id);
        if (exists) {
            repository.deleteById(id);
            Map<String, Object> response = new HashMap<>();
            String message = entityName + " Eliminado";
            response.put("message", message);
            return response;
        }
        throw new IdNoExist(id.toString(), entityName);
    }
}
