package br.unitins.topicosii.application;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

import org.primefaces.model.ScheduleDisplayMode;
import org.primefaces.model.ScheduleEvent;

import br.unitins.topicosii.models.Agendamento;

public class CustomScheduleEvent implements ScheduleEvent<Agendamento>{

    private String id;
    private String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String styleClass;
    private Agendamento data;
    private String url;
    private String description;
    private boolean allDay;
    private boolean editable;

    public CustomScheduleEvent() {
    }

    public CustomScheduleEvent(String title, LocalDateTime start, LocalDateTime end, boolean allDay, Agendamento data) {
        this.title = title;
        this.startDate = start;
        this.endDate = end;
        this.allDay = allDay;
        this.data = data;
    }

    public CustomScheduleEvent(String title, LocalDateTime start, LocalDateTime end, String styleClass, boolean allDay, Agendamento data) {
        this.title = title;
        this.startDate = start;
        this.endDate = end;
        this.styleClass = styleClass;
        this.allDay = allDay;
        this.data = data;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

	public Agendamento getData() {
		return data;
	}

	public void setData(Agendamento data) {
		this.data = data;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isAllDay() {
		return allDay;
	}

	public void setAllDay(boolean allDay) {
		this.allDay = allDay;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	@Override
	public String getGroupId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ScheduleDisplayMode getDisplay() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getBackgroundColor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getBorderColor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTextColor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean isDraggable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean isResizable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isOverlapAllowed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Map<String, Object> getDynamicProperties() {
		// TODO Auto-generated method stub
		return null;
	}

}
