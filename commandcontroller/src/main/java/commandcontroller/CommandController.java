package commandcontroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommandController {

    @RequestMapping("/getappointments")
    public GetAppointmentsProcessor getAppointmentsProcessor(@RequestParam(value="id", defaultValue="") String id) {
        return new GetAppointmentsProcessor(id);
    }
}
