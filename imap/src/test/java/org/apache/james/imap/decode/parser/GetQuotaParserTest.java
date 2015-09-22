/****************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one   *
 * or more contributor license agreements.  See the NOTICE file *
 * distributed with this work for additional information        *
 * regarding copyright ownership.  The ASF licenses this file   *
 * to you under the Apache License, Version 2.0 (the            *
 * "License"); you may not use this file except in compliance   *
 * with the License.  You may obtain a copy of the License at   *
 *                                                              *
 *   http://www.apache.org/licenses/LICENSE-2.0                 *
 *                                                              *
 * Unless required by applicable law or agreed to in writing,   *
 * software distributed under the License is distributed on an  *
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY       *
 * KIND, either express or implied.  See the License for the    *
 * specific language governing permissions and limitations      *
 * under the License.                                           *
 ****************************************************************/

package org.apache.james.imap.decode.parser;

import org.apache.james.imap.api.ImapCommand;
import org.apache.james.imap.decode.ImapRequestStreamLineReader;
import org.apache.james.imap.message.request.GetQuotaRequest;
import org.apache.james.protocols.imap.DecodingException;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

/**
 * GetQuotaRootParser Test
 */
public class GetQuotaParserTest {

    @Test
    public void testQuotaParsing() throws DecodingException {
        GetQuotaCommandParser parser = new GetQuotaCommandParser();
        ImapCommand command = ImapCommand.anyStateCommand("Command");
        String commandString = "quotaRoot \n";
        InputStream inputStream = new ByteArrayInputStream(commandString.getBytes());
        ImapRequestStreamLineReader lineReader = new ImapRequestStreamLineReader(inputStream, null);
        GetQuotaRequest request = (GetQuotaRequest) parser.decode(command, lineReader, "A003", null);
        GetQuotaRequest expected = new GetQuotaRequest("A003", command, "quotaRoot");
        assertEquals(expected.getQuotaRoot(), request.getQuotaRoot());
    }
}
