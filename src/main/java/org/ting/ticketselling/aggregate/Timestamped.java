package org.ting.ticketselling.aggregate;

import java.time.ZonedDateTime;

public interface Timestamped {

	public ZonedDateTime getCreatedAt();

	public void setCreatedAt(ZonedDateTime createdAt);

	public ZonedDateTime getUpdatedAt();

	public void setUpdatedAt(ZonedDateTime updatedAt);
}
