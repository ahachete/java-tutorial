/*
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the
 * following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following
 * disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the
 * following disclaimer in the documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES,
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 */


package org.postgresql.ext.javatutorial.common.util;


import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.infra.BenchmarkParams;
import org.openjdk.jmh.results.RunResult;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.VerboseMode;

import java.util.Collection;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


public class MainUtil {
    private static final int ITERATIONS = 3;
    private static final int FORKS = 2;

    public static void run(Class<?> clazz) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(clazz.getSimpleName())
                .forks(FORKS)
                .measurementIterations(ITERATIONS)
                .timeUnit(TimeUnit.MILLISECONDS)
                .mode(Mode.SingleShotTime)
                .verbosity(VerboseMode.SILENT)
                .build();

        Collection<RunResult> runResults = new Runner(opt).run();

        runResults.stream().forEach(runResult ->
                System.out.printf(
                        "Avg(ms):\t%s\t%s\t%.2f ± %.2f\n",
                        runResult.getParams().getBenchmark(),
                        testParams(runResult),
                        runResult.getPrimaryResult().getScore(),
                        runResult.getPrimaryResult().getScoreError()
                )
        );
    }

    private static String testParams(RunResult runResult) {
        BenchmarkParams params = runResult.getParams();
        if(null == params) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        return params.getParamsKeys().stream()
                .map(p ->
                        sb.append(p).append("=").append(params.getParam(p))
                ).collect(Collectors.joining(", "));
    }
}
