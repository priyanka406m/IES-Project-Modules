package in.ashokit.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import in.ashokit.binding.IESAppCreateForm;
import in.ashokit.entity.IESApplCreateEntity;
import in.ashokit.repository.ApplicationRegRepository;

@Service
public class ApplicationRegServiceImpl implements ApplicationRegService {

	@Autowired
	private ApplicationRegRepository repository;

	@Override
	public boolean createApplRegForm(IESAppCreateForm form) {

		String checkUserDetails = "http://65.2.166.5:8080/ssa/citizen";

		WebClient webClient = WebClient.create();

		try {
			IESApplCreateEntity entity = webClient.post().uri(checkUserDetails).header("Accept", "application/json")
					.bodyValue(form).retrieve().bodyToMono(IESApplCreateEntity.class).block();

			if (entity == null) {
				return false;
			}
			/*IESUserEntity userEntity = repository.findByUserId(form.getUserId());
			System.out.println("userEntity ####:"+userEntity.getUserId());
			entity.setUserId(userEntity.getUserId());

*/			BeanUtils.copyProperties(form, entity);
			entity.setCreatedBy(1);

			if (entity.getState().equals("Rhode Island")) {
				repository.save(entity);
			}else {
				return false;
			}

		} catch (WebClientResponseException.MethodNotAllowed ex) {
			System.err.print(ex);

		}
		return true;

	}

}
