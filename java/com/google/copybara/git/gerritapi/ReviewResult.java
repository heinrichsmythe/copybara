/*
 * Copyright (C) 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.copybara.git.gerritapi;

import com.google.api.client.util.Key;
import com.google.common.collect.ImmutableMap;
import com.google.devtools.build.lib.skylarkinterface.SkylarkCallable;
import com.google.devtools.build.lib.skylarkinterface.SkylarkModule;
import com.google.devtools.build.lib.skylarkinterface.SkylarkModuleCategory;
import com.google.devtools.build.lib.syntax.SkylarkDict;
import java.util.Map;

/** https://gerrit-review.googlesource.com/Documentation/rest-api-changes.html#review-result */
@SuppressWarnings("unused")
@SkylarkModule(
    name = "gerritapi.ReviewResult",
    category = SkylarkModuleCategory.TOP_LEVEL_TYPE,
    doc = "Gerrit review result.")
public class ReviewResult {
  @Key private Map<String, Integer> labels;
  @Key private boolean ready;

  public ReviewResult(Map<String, Integer> labels, boolean ready) {
    this.labels = labels;
    this.ready = ready;
  }

  public ReviewResult() {
  }

  @SkylarkCallable(
      name = "labels",
      doc = "Map of labels to values after the review was posted.",
      structField = true,
      allowReturnNones = true)
  public SkylarkDict<String, Integer> getLabelsForSkylark() {
    return SkylarkDict.copyOf(/*environment*/ null, getLabels());
  }

  public ImmutableMap<String, Integer> getLabels() {
    return labels == null ? ImmutableMap.of() : ImmutableMap.copyOf(labels);
  }

  @SkylarkCallable(
    name = "ready",
    doc =
        "If true, the change was moved from WIP to ready for review as a result of this action."
            + " Not set if false.",
      structField = true
  )
  public boolean isReady() {
    return ready;
  }
}
