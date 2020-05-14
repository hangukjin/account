package local;

import local.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{

    @Autowired
    AccountingRepository accountingRepository;

    @StreamListener(KafkaProcessor.INPUT)
        public void wheneverPaymentCompleted_SalesAdd(@Payload OrderReceived orderReceived){

            if(orderReceived.isMe()){
                System.out.println("##### listener SalesAdd : " + orderReceived.toJson());

                String yearMonth = orderReceived.getTimestamp().substring(0, 8);

                Accounting accounting;
                if (accountingRepository.findById(yearMonth).isPresent())
                {
                    accounting = accountingRepository.findById(yearMonth).get();
                    accounting.setOrderCount(accounting.getOrderCount() + 1);
                    accounting.setSalesSum(accounting.getSalesSum() + orderReceived.getPrice());
                    accounting.setSalesQty(accounting.getSalesQty() + orderReceived.getQty());
                    accountingRepository.save(accounting);
                } else {
                    accounting = new Accounting();
                    accounting.setYearmonth(yearMonth);
                    accounting.setOrderCount((double) 1);
                    accounting.setSalesSum((double) orderReceived.getPrice());
                    accounting.setSalesQty((double) orderReceived.getQty());
                    accountingRepository.save(accounting);
                }

            }
    }

}
