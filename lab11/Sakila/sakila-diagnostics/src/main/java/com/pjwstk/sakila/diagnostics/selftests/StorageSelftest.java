package com.pjwstk.sakila.diagnostics.selftests;

import com.pjwstk.sakila.diagnostics.contract.SelftestResult;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component
public class StorageSelftest extends DefaultSelftest{
    private static final int REQUIRED_DISK_PERCENTAGE = 5;

    public StorageSelftest() {
        name = "StorageSelftest";
        description = "Is there at least 5% free";
    }

    @Override
    public SelftestResult execute() {
        List<String> errors = new ArrayList<>();
        SelftestResult selftestResult = new SelftestResult(name, description, false, errors);

        if(getUsableDiscSpacePercentage() >= REQUIRED_DISK_PERCENTAGE) selftestResult.setPassed(true);
        else {
            selftestResult.setPassed(false);
            errors.add(getUsableDiscSpacePercentage() - REQUIRED_DISK_PERCENTAGE + "% more space is required");
        }

        return selftestResult;
    }

    public long getUsableDiscSpacePercentage(){
        File file = new File("d:");
        long totalSpace = file.getTotalSpace();
        long usableSpace = file.getUsableSpace();
        return (usableSpace / totalSpace) * 100;
    }
}
