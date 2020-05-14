package local;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RequestMethod;

@Service
@FeignClient(name ="headquarters", url="localhost:8086")
public interface headquarterSend {

    @RequestMapping(method = RequestMethod.POST, value = "/headquaters", consumes = "application/json")
    void headquarter2Send(@RequestBody SalesTransferred salesTransferred);

}