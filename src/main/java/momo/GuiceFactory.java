/*
 * MIT License
 *
 * Copyright (c) 2023 Daniel Hintz
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package momo;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.commons.lang3.SystemUtils;

import com.google.inject.AbstractModule;
import com.google.inject.ConfigurationException;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Provides;

import momo.services.FileBasedMonthlyHoursService;
import momo.services.MonthlyHoursService;
import picocli.CommandLine;
import picocli.CommandLine.IFactory;

/**
 * @see <a href="https://picocli.info/#_guice_example">Picocli - Guice Example<a/>
 */
public class GuiceFactory implements IFactory
{
    private final Injector injector = Guice.createInjector(new BasicModule());

    @Override
    public <K> K create(Class<K> aClass) throws Exception
    {
        try
        {
            return injector.getInstance(aClass);
        }
        catch (ConfigurationException ex)
        {
            // no implementation found in Guice configuration
            return CommandLine.defaultFactory().create(aClass); // fallback if missing
        }
    }

}