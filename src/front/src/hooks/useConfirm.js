import { useState } from 'react';

const useConfirm = () => {
	const [isOpen, toggleIsOpen] = useState(true);
	const [hasConfirmed, setConfirmed] = useState(false);

	return {
		isOpen,
		hasConfirmed,
		open() {
			toggleIsOpen(true);
		},
		close() {
			toggleIsOpen(false);
		},
 		confirm() {
			setConfirmed(true);
		},
		cancel() {
			setConfirmed(false);
		}
	}
}

export default useConfirm;