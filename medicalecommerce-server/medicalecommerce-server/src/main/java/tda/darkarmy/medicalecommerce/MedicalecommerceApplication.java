package tda.darkarmy.medicalecommerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import tda.darkarmy.medicalecommerce.model.Role;
import tda.darkarmy.medicalecommerce.model.Roles;
import tda.darkarmy.medicalecommerce.repository.RolesRepository;

@SpringBootApplication
public class MedicalecommerceApplication {
	@Autowired
	private RolesRepository rolesRepository;
	public static void main(String[] args) {
		SpringApplication.run(MedicalecommerceApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void initializeRoles() {
		if(rolesRepository.findByRoleName(Role.USER)==null)
			rolesRepository.save(new Roles(Role.USER));
		if(rolesRepository.findByRoleName(Role.VENDOR)==null)
			rolesRepository.save(new Roles(Role.VENDOR));
		if(rolesRepository.findByRoleName(Role.ADMIN)==null)
			rolesRepository.save(new Roles(Role.ADMIN));

	}

}
