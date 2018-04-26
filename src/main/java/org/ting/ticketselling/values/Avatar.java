package org.ting.ticketselling.values;

import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Avatar {

	public static Avatar create(String sourceName, @Nullable String externalUri) {
		return new AutoValue_Avatar(sourceName, externalUri);
	}

	@JsonProperty("name")
	public abstract String sourceName();

	@JsonProperty("link")
	@Nullable
	public abstract String externalUri();

	public String getFullPath() {
		if (this.externalUri() != null) {
			return this.externalUri() + this.sourceName();
		} else {
			return this.sourceName();
		}
	}
}
