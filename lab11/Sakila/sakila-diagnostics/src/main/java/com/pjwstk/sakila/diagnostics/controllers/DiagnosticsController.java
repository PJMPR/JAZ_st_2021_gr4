package com.pjwstk.sakila.diagnostics.controllers;

import com.pjwstk.sakila.diagnostics.selftests.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

@Controller
@RequestMapping("diagnostics")
public class DiagnosticsController {

    @GetMapping("status")
    public ResponseEntity status(){
        return ResponseEntity.ok("ALIVE");
    }

    @GetMapping("selftest")
    public ResponseEntity selftest(){
        RunSelftest selfTestRun = new RunSelftest();
        selfTestRun.setSelftestList(Arrays.asList(new StorageSelftest(),
                new SakilaConnectionSelftest(),
                new ImDBServiceSelftest(),
                new ThemoviedbSelftest()));
        return ResponseEntity.ok(RunSelftest.execute());
    }
}
