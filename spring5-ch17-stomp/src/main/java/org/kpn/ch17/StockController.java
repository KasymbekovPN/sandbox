package org.kpn.ch17;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Controller
public class StockController {

    private static final Logger logger = LoggerFactory.getLogger(StockController.class);

    private TaskScheduler taskScheduler;
    private SimpMessagingTemplate simpMessagingTemplate;

    private final List<Stock> stocks = new ArrayList<>();
    private final Random random = new Random(System.currentTimeMillis());

    public StockController() {
        logger.info("CONSTRUCTOR");
        stocks.add(new Stock("VMW", 1.0));
        stocks.add(new Stock("EMC", 1.0));
        stocks.add(new Stock("GOOG", 1.0));
        stocks.add(new Stock("IBM", 1.0));
    }

    @MessageMapping("/addStock")
    public void addStock(Stock stock){
        logger.info("ADD_STOCK: {}", stock);
        stocks.add(stock);
        broadcastUpdatedPrices();
    }

    @Autowired
    public void setSimpMessagingTemplate(SimpMessagingTemplate simpMessagingTemplate){
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @Autowired
    public void setTaskScheduler(TaskScheduler taskScheduler){
        this.taskScheduler = taskScheduler;
    }

    private void broadcastUpdatedPrices(){
        logger.info("BROADCAST");
        for (Stock stock : stocks) {
            stock.setPrice(stock.getPrice() + (getUpdatedStockPrice() * stock.getPrice()));
            stock.setDate(new Date());
        }

        simpMessagingTemplate.convertAndSend("/topic/price", stocks);
    }

    private double getUpdatedStockPrice(){
        double priceChange = random.nextDouble() * 5.0;
        if (random.nextInt(2) == 1){
            priceChange = -priceChange;
        }

        return priceChange / 100.0;
    }

    @PostConstruct
    private void broadcastTimePeriodically(){
        taskScheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                broadcastUpdatedPrices();
            }
        }, 1000);
    }
}
