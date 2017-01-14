package org.obsplatform.infrastructure.jobs.handler;

import org.obsplatform.commands.handler.NewCommandSourceHandler;
import org.obsplatform.infrastructure.core.api.JsonCommand;
import org.obsplatform.infrastructure.core.data.CommandProcessingResult;
import org.obsplatform.infrastructure.jobs.service.SchedularWritePlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateJobDetailCommandhandler implements NewCommandSourceHandler {

    private final SchedularWritePlatformService schedularWritePlatformService;

    @Autowired
    public UpdateJobDetailCommandhandler(final SchedularWritePlatformService schedularWritePlatformService) {
        this.schedularWritePlatformService = schedularWritePlatformService;
    }

    @Override
    public CommandProcessingResult processCommand(JsonCommand command) {
        return this.schedularWritePlatformService.updateJobDetail(command.entityId(), command);
    }

}
