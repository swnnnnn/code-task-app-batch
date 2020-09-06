package code.task.app.batch.controller;

import code.task.app.batch.service.RankService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

/**
 * RankController
 *
 * @author 최상원
 * @version 1.0
 * <pre>
 * 2020.09.01 : 최초 작성
 * </pre>
 * @since 2020.09.01
 */
@Slf4j
@AllArgsConstructor
@Controller
public class RankController {

    private final RankService rankService;

    @Scheduled(cron = "0 0 01 * * *") // 새벽1시
    public void updateRank() throws Exception {
        rankService.updateRank();
    }
}