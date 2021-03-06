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
 * @(#)mqcallback-types-priv.h	1.9 06/26/07
 */ 

#ifndef MQ_CALLBACK_TYPES_PRIV_H
#define MQ_CALLBACK_TYPES_PRIV_H

#ifdef __cplusplus
extern "C" {
#endif /* __cplusplus */

#include "mqtypes.h"
#include "mqcallback-types.h"


/**
 * This callback is used to notify the user that a message has
 * arrived.
 *
 * @param sessionHandle the session containing the consumer that
 *        received a message
 * @param consumerHandle the consumer that received a message
 * @param callbackData whatever void* pointer that was passed to
 *        MQSetMessageArrivedFunc
 * @see MQSetMessageArrivedFunc. */
typedef void (*MQMessageArrivedFunc)(const MQSessionHandle  sessionHandle,
                                      const MQConsumerHandle consumerHandle,
                                            void *            callbackData );

/** The type of the entry point for the thread creating function */
typedef void (*MQThreadFunc)(void *arg);

/**
 * This callback allows the user to specify a callback function that
 * creates threads.
 *
 * @param startFunc the starting function for the thread
 * @param arg the argument to pass to 
 * @param callbackData whatever void* pointer that was passed to
 *        MQSetCreateThreadFunc
 * @param MQ_TRUE if the thread was successfully created and
 *        MQ_FALSE if the thread could not be created.
 * @see MQSetCreateThreadFunc. */
typedef MQBool (*MQCreateThreadFunc)(MQThreadFunc startFunc,
                                       void * arg,     
                                       void * callbackData);

/* The relative logging severity. */
typedef enum _MQLoggingLevel {MQ_LOG_OFF  = -1,
                              MQ_LOG_SEVERE  = 0, 
                              MQ_LOG_WARNING = 1,
                              MQ_LOG_INFO    = 2, 
                              MQ_LOG_CONFIG  = 3, 
                              MQ_LOG_FINE    = 4,
                              MQ_LOG_FINER   = 5,
                              MQ_LOG_FINEST  = 6 } MQLoggingLevel;
/**
 * This is the type of the user installed callback function that can be
 * used to log information generated by the MQ library.
 *
 * @param severity the severity of the logging message
 * @param logCode the error code associated with the error.  This
 *        is only guaranteed to be valid if severity is MQSevereLevel.
 * @param logMessage the message to log
 * @param timeOfMessage the time of the log message represented as the number
 *        of microseconds since the, midnight (00:00:00) 1 January 1970 UTC. 
 * @param connectionID the id of the connection that generated this logging
 *        message.  This will be 0 if no connection can be associated with
 *        the log message.
 * @param filename the name of the source file where the log message originated
 * @param fileLineNumber the line number in the source file where the log
 *        message originated
 * @param callbackData the void* pointer that was passed to MQSetLoggingFunc
 * @see MQSetLoggingFunc
 */
typedef void (*MQLoggingFunc)(const MQLoggingLevel  severity,
                               const MQInt32         logCode,
                               ConstMQString         logMessage,
                               const MQInt64         timeOfMessage,
                               const MQInt64         connectionID,
                               ConstMQString         filename,
                               const MQInt32         fileLineNumber,
                                     void*            callbackData);

#ifdef __cplusplus
}
#endif /* __cplusplus */

#endif /* MQ_CALLBACK_TYPES_PRIV_H */
