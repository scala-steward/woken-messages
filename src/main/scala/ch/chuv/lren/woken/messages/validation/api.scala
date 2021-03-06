/*
 * Copyright (C) 2017  LREN CHUV for Human Brain Project
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package ch.chuv.lren.woken.messages.validation

import ch.chuv.lren.woken.messages.RemoteMessage
import ch.chuv.lren.woken.messages.variables.VariableMetaData
import spray.json.{ JsObject, JsValue }
import cats.data.NonEmptyList

// TODO: test data should come from the database
/**
  *
  * @param fold    Name of the fold
  * @param pfaModel PFA model
  * @param data    Test data
  * @param varInfo Metadata for the variable to validate
  */
case class ValidationQuery(
    fold: Int,
    pfaModel: JsObject,
    data: List[JsValue],
    varInfo: VariableMetaData
) extends RemoteMessage

case class ValidationResult(
    fold: Int,
    varInfo: VariableMetaData,
    result: Either[String, List[JsValue]]
)

// TODO: the NonEmptyList[String] contains actually a Json value to deserialise and that maps usually to String or Double
case class ScoringQuery(algorithmOutput: NonEmptyList[JsValue],
                        groundTruth: NonEmptyList[JsValue],
                        targetMetaData: VariableMetaData)
    extends RemoteMessage

case class ScoringResult(
    result: Either[String, Score]
)
