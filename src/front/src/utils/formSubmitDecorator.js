export const handleSubmitDecorator = (handleSubmit, event, reset, close) => {
	return () => {
		event.preventDefault()
		handleSubmit()
			.then(() => {
				reset();
				close();
			});
	}
}
	