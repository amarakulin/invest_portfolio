import { useState } from 'react';

const useConfirm = (confirmCallback) => {
	const [isOpen, toggleIsOpen] = useState(false);

	return {
		isOpen,
		open() {
			toggleIsOpen(true);
		},
		close() {
			toggleIsOpen(false);
		},
 		confirm() {
			this.close();
			confirmCallback();
		},
		cancel() {
			this.close();
		}
	}
}

export default useConfirm;