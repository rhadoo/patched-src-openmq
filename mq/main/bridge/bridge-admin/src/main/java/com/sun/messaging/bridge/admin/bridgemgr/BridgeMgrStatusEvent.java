/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2000-2017 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * https://oss.oracle.com/licenses/CDDL+GPL-1.1
 * or LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */

/*
 */ 

package com.sun.messaging.bridge.admin.bridgemgr;

import com.sun.messaging.jmq.admin.event.CommonCmdStatusEvent;

/**
 * Event class indicating some actions related to
 * Bridge Management.
 *<P>
 * The fields of this event include the various pieces of information
 * needed for broker management tasks.
 */
public class BridgeMgrStatusEvent extends CommonCmdStatusEvent {

    /*******************************************************************************
     * BridgeMgrStatusEvent event types
     * use integers 5000 - 5999  
     *******************************************************************************/
    public enum Type {
        ;
        public final static int HELLO  = 5000;
        public final static int LIST   = 5001;
        public final static int START  = 5002;
        public final static int STOP   = 5003;
        public final static int RESUME = 5004;
        public final static int PAUSE  = 5005;
        public final static int DEBUG  = 5006;
    }

    private transient BridgeAdmin  ba;

    /**
     * @param source the object where the event originated
     * @type the event type
     */
    public BridgeMgrStatusEvent(Object source, int type) {
	super(source, type);
    }

    /**
     * @param source the object where the event originated
     * @type the event type
     */
    public BridgeMgrStatusEvent(Object source, BridgeAdmin ba, int type) {
	super(source, type);
	setBridgeAdmin(ba);
    }

    public void setBridgeAdmin(BridgeAdmin ba) {
	this.ba = ba;
    }
    public BridgeAdmin getBridgeAdmin() {
	return (ba);
    }
   
}
